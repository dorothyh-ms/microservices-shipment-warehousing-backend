package be.kdg.prog6.waterside.core;

import be.kdg.prog6.waterside.domain.BunkeringOperation;

import be.kdg.prog6.waterside.domain.BunkeringOperationDaySchedule;
import be.kdg.prog6.waterside.exceptions.BunkeringOperationNotFoundException;
import be.kdg.prog6.waterside.ports.in.PlanBunkeringOperationUseCase;
import be.kdg.prog6.waterside.ports.out.BunkeringOperationDayScheduleLoadPort;
import be.kdg.prog6.waterside.ports.out.BunkeringOperationLoadPort;
import be.kdg.prog6.waterside.ports.out.BunkeringOperationUpdatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultPlanBunkeringOperationUseCase implements PlanBunkeringOperationUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCompleteBunkeringOperationUseCase.class);

    private final BunkeringOperationLoadPort bunkeringOperationLoadPort;

    private final BunkeringOperationUpdatePort bunkeringOperationUpdatePort;

    private final BunkeringOperationDayScheduleLoadPort bunkeringOperationDayScheduleLoadPort;

    public DefaultPlanBunkeringOperationUseCase(BunkeringOperationLoadPort bunkeringOperationLoadPort, BunkeringOperationUpdatePort bunkeringOperationUpdatePort, BunkeringOperationDayScheduleLoadPort bunkeringOperationDayScheduleLoadPort) {
        this.bunkeringOperationLoadPort = bunkeringOperationLoadPort;
        this.bunkeringOperationUpdatePort = bunkeringOperationUpdatePort;
        this.bunkeringOperationDayScheduleLoadPort = bunkeringOperationDayScheduleLoadPort;
    }

    public BunkeringOperation planBunkeringOperation(UUID shippingOrderId, LocalDate plannedDate) {
        LOGGER.info("DefaultPlanBunkeringOperationUseCase is running planBunkeringOperation with shipping order ID {} and planned date {}", shippingOrderId, plannedDate);
        Optional<BunkeringOperation> boOptional = bunkeringOperationLoadPort.loadBunkeringOperationByShippingOrderId(shippingOrderId);
        if (boOptional.isEmpty()){
            throw new BunkeringOperationNotFoundException(String.format("No bunkering operation of shipping order with id {} ", shippingOrderId.toString()));
        }
        BunkeringOperation bo = boOptional.get();
        BunkeringOperationDaySchedule daySchedule = bunkeringOperationDayScheduleLoadPort.loadBunkeringOperationDaySchedule(plannedDate);
        daySchedule.planBunkeringOperation(bo);
        bunkeringOperationUpdatePort.updateBunkeringOperation(bo);
        return bo;
    };
}
