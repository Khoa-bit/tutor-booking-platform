# Tutor booking platform

## How to run project

### Git Pre-commit hook

First, change directory to /tutor-booking-platform and install dependencies

```bash
cd tutor-booking-platform
yarn
```

### Brooks (Frontend):

First, change directory to /client/brooks and install dependencies:

```bash
cd client/brooks
yarn
```

Second, run the development server:

```bash
yarn dev
```

Open [http://localhost:3000](http://localhost:3000) with your browser to see NextJS client in action.

### Baked (Backend):

First, have Java 17 installed and selected on your computer.

Second, change directory to /server/baked and run SpringBoot server

```bash
cd server/baked
./mvnw spring-boot:run
```

Open [http://localhost:8080](http://localhost:8080) with your browser to see SpringBoot APIs in action.
