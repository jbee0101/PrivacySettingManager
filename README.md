# Privacy Settings Manager

## Overview

A sample Android app that demonstrates managing privacy settings. This project is built as part of an interview task, showcasing best practices using modern Android development tools and architecture.

The app simulates API interaction by loading data from a local JSON file (`assets/services.json`), structured to resemble a real API.


## Why This Architecture?

I have used this architecture for a clear separation of concerns and maintainability. With the MVVM pattern, the UI layer (Jetpack Compose) is kept reactive and independent of business logic, which is handled in ViewModels. There is one ViewModel per screen to follow the single responsibility principle.
Data access is encapsulated in a repository pattern to be able to switch between mock and real data sources without hassle. Dependency injection is managed by Hilt, which makes the code more testable and modular. Predictable state updates and lifecycle awareness are addressed using StateFlow, which fits Compose's reactive model naturally


## What's Done

- [x] App architecture follows **MVVM + Clean separation**
- [x] UI built using **Jetpack Compose**
- [x] Navigation between **Home** and **Detail** screens using Navigation-Compose
- [x] Used **Retrofit** structure + OkHttp Interceptor to simulate API with local `services.json`
- [x] Displayed service icons using **Coil**
- [x] Toggling of settings supported and persisted using **DataStore**
- [x] Separate **ViewModels** for Home and Detail screen
- [x] Hilt used for Dependency Injection
- [x] Fully reactive with **StateFlow** in ViewModels
- [x] Improve UI


## What's Remaining / Future Improvements

- [ ] Add **unit tests** for ViewModels and DataStore logic
- [ ] Add **UI tests** using Jetpack Compose Testing
- [ ] Add meaningful **inline code comments** for clarity and maintainability

## How to Run the Project
1. Clone the repository.
2. Open the project in Android Studio.
3. Sync dependencies.
4. Run the app on an emulator or a physical device.