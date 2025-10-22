## Project Overview

This backend system provides the foundations for shipment and warehousing operations via microservices.  
Core capabilities include:

- Managing shipments (creation, tracking, status updates)
- Warehousing operations (inventory, receipt, put-away, picking, shipping)
- Support for landside, waterside, and cross-domain logistics within a warehouse/shipment ecosystem
- Built with Java and Spring Framework (based on repository file structure)

The goal is to enable a modular, scalable architecture where different services handle distinct concerns, facilitating independent deployment, scaling, and maintenance.

---

## Architecture

The project follows a microservices architecture.  
Key architectural notes:

- Each service is developed as a separate module under the `application/` directory (e.g., `landside`, `waterside`, `warehouse`).
- Common/shared code lives in the `common/` module (utilities, DTOs, constants).
- Infrastructure concerns (e.g., messaging, database, configuration) are handled via the `infrastructure/` module.
- Build system: Gradle wrapper present (`gradlew`, `gradlew.bat`, `buildSrc`, `gradle/wrapper`).
- The repository is structured to allow independent service launches yet share build and configuration conventions.

---

## Modules / Services

Below is a high-level list of modules/services included:

- **common** – Shared utilities, domain objects, DTOs, common exceptions, etc.
- **infrastructure** – Support infrastructure such as database schemas, messaging/broker configs, external integrations.
- **landside** – Service managing landside shipments (e.g., truck, rail deliveries to/from warehouse).
- **waterside** – Service handling waterside shipments (e.g., sea freight, port operations).
- **warehouse** – Warehouse management service: inventory, receipting, picking, put-away, shipping.


