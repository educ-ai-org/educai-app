# Educ.AI

Educ.AI is an AI-powered educational platform designed to enhance the teaching and learning experience. This native Android application built with Jetpack Compose provides teachers with intelligent tools to create educational materials, manage classrooms, assign activities, and interact with an AI assistant specialized in education.

## 📋 Table of Contents

- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [Architecture](#-architecture)
- [Prerequisites](#-prerequisites)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [Usage](#-usage)
- [Project Structure](#-project-structure)
- [API Integration](#-api-integration)
- [Development](#-development)
- [License](#-license)

## ✨ Features

### Authentication & Security
- **User Authentication**: Secure login with JWT token-based authentication
- **Token Management**: Automatic token refresh and session management
- **Encrypted Storage**: Secure credential storage using AndroidX Security Crypto
- **Auto-login**: Persistent sessions with automatic token renewal

### Classroom Management
- **Multiple Classrooms**: View and manage multiple classes simultaneously
- **Classroom Details**: Access comprehensive information about each class including course name, student count, and upcoming activities
- **Class Navigation**: Intuitive navigation between different classrooms
- **Student Management**: View all participants in a classroom with their profiles

### Activity Management
- **Create Activities**: Teachers can create and assign educational activities to students
- **Activity List**: View all activities for a classroom with status indicators (pending, completed, late)
- **Activity Execution**: Interactive interface for students to complete activities
- **Question System**: Support for multiple-choice questions with immediate feedback
- **Activity Review**: View completed activities with scores and correct answers
- **Submission Tracking**: Monitor activity submissions and deadlines
- **Progress Tracking**: Visual indicators for activity completion status

### AI-Powered Material Generation
- **Intelligent Material Creation**: Generate educational content using AI from multiple sources:
  - YouTube video links
  - Audio file uploads (voice recordings)
  - Document uploads (PDF, DOCX, etc.)
  - Text-based instructions
- **Multimodal Input**: Combine different input types for comprehensive material generation
- **AI Provider Selection**: Choose between different AI models (OpenAI)

### AI Assistant - "Fale com Edu"
- **Conversational AI**: Chat interface with an AI assistant specialized in education
- **Voice Input**: Speech-to-text integration for hands-free interaction
- **Real-time Responses**: Get instant answers to educational questions
- **Message History**: Persistent conversation history with timestamps
- **Audio Permission Management**: Secure microphone access for voice interactions

### Dictionary Integration
- **Word Lookup**: Built-in dictionary for quick word definitions
- **Multiple Meanings**: Display various definitions and usage examples
- **Search Functionality**: Quick word search directly from the app
- **Educational Context**: Definitions optimized for learning environments

### Leaderboard & Gamification
- **Student Rankings**: View top-performing students in each classroom
- **Score Tracking**: Real-time point accumulation based on activity performance
- **Profile Pictures**: Visual representation of students in rankings
- **Competitive Learning**: Motivate students through friendly competition

### Posts & Announcements
- **Classroom Feed**: View posts and announcements from teachers
- **File Attachments**: Support for various file types in posts
- **Date Tracking**: Timestamp for all posts and announcements
- **Rich Content**: Descriptions and formatted content in posts

### User Interface
- **Modern Design**: Material Design 3 implementation with custom theming
- **Gradient Backgrounds**: Beautiful purple gradient theme throughout the app
- **Dark/Light Mode**: Support for system theme preferences
- **Responsive Layouts**: Optimized for different screen sizes
- **Bottom Navigation**: Easy access to main features
- **Drawer Menu**: Side navigation for quick feature access
- **Custom Components**: Reusable UI components for consistent experience

### Offline Capabilities
- **Token Caching**: Secure local storage of authentication tokens
- **State Management**: LiveData and ViewModel for efficient data handling

## 🛠 Technology Stack

### Core Technologies
- **Language**: Kotlin 1.9.0
- **UI Framework**: Jetpack Compose (Material Design 3)
- **Build System**: Gradle with Kotlin DSL
- **Minimum SDK**: Android 11 (API 30)
- **Target SDK**: Android 14 (API 34)

### Android Jetpack Libraries
- **Compose BOM**: 2024.04.01
- **Navigation Compose**: 2.7.7 - Type-safe navigation between screens
- **Lifecycle Runtime**: 2.8.4 - Lifecycle-aware components
- **Activity Compose**: 1.9.1 - Compose integration with activities
- **Core KTX**: 1.13.1 - Kotlin extensions

### Networking
- **Retrofit**: 2.9.0 - REST API client
- **OkHttp**: 4.9.3 - HTTP client with interceptors
- **Gson Converter**: 2.9.0 - JSON serialization/deserialization
- **Scalars Converter**: 2.9.0 - String response handling
- **Logging Interceptor**: 4.9.1 - Network request/response logging

### Security
- **Security Crypto**: 1.1.0-alpha06 - Encrypted SharedPreferences for token storage

### Image Loading
- **Coil**: 2.6.0 - Efficient image loading and caching for Compose

### UI Enhancements
- **Accompanist System UI Controller**: 0.27.0 - System bar customization

### Testing
- **JUnit**: 4.13.2 - Unit testing
- **AndroidX JUnit**: 1.2.1 - Android instrumented tests
- **Espresso**: 3.6.1 - UI testing

## 🏗 Architecture

### Pattern: MVVM (Model-View-ViewModel)

The application follows the MVVM architecture pattern with clear separation of concerns:

```
┌─────────────────────────────────────────────────────┐
│                    Presentation Layer                │
│  ┌───────────────────────────────────────────────┐  │
│  │         Screens (Composables)                 │  │
│  │  - Login, Home, Classroom Details             │  │
│  │  - Activity, Material Creation, Chat          │  │
│  └───────────────────────────────────────────────┘  │
│                        ↕                             │
│  ┌───────────────────────────────────────────────┐  │
│  │            ViewModels                          │  │
│  │  - AuthViewModel, UserViewModel                │  │
│  │  - ClassworkViewModel, IAViewModel            │  │
│  │  - MaterialViewModel, LeaderboardViewModel    │  │
│  └───────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────┘
                         ↕
┌─────────────────────────────────────────────────────┐
│                    Domain Layer                      │
│  ┌───────────────────────────────────────────────┐  │
│  │              Data Models                       │  │
│  │  - User, Classroom, Classwork                  │  │
│  │  - Post, Message, Question                     │  │
│  └───────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────┘
                         ↕
┌─────────────────────────────────────────────────────┐
│                     Data Layer                       │
│  ┌───────────────────────────────────────────────┐  │
│  │              Services (API)                    │  │
│  │  - AuthService, UserService                    │  │
│  │  - ClassworkService, IAService                 │  │
│  │  - MaterialService, PostService                │  │
│  └───────────────────────────────────────────────┘  │
│                        ↕                             │
│  ┌───────────────────────────────────────────────┐  │
│  │            Retrofit Client                     │  │
│  │  - Base URL: api.educai.xyz                    │  │
│  │  - AI URL: ai.educai.xyz                       │  │
│  │  - Token Interceptor                           │  │
│  └───────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────┘
```

### Key Components

- **MainActivity**: Single activity hosting all Compose screens with navigation
- **Screens**: Composable functions representing different app screens
- **ViewModels**: Handle business logic and state management using LiveData
- **Services**: Retrofit interfaces defining API endpoints
- **Models**: Data classes representing API responses and requests
- **Components**: Reusable UI components (buttons, cards, modals, etc.)
- **Utils**: Helper functions and data transformation utilities
- **Contexts**: Shared contexts like TokenManager for cross-cutting concerns

## 📋 Prerequisites

### Development Environment
- **Android Studio**: Hedgehog (2023.1.1) or later
- **JDK**: Java 11 or higher
- **Gradle**: 8.5.0 (included via wrapper)

### Device Requirements
- **Minimum Android Version**: Android 11 (API 30)
- **Recommended**: Android 12 or higher
- **Permissions Required**:
  - Internet access
  - Microphone access (for voice features)
  - Storage access (for file uploads)

### API Access
- Access to Educ.AI backend API (api.educai.xyz)
- Access to Educ.AI AI services (ai.educai.xyz)

## 🚀 Installation

### 1. Clone the Repository

```bash
git clone https://github.com/educ-ai-org/educai-app.git
cd educai-app
```

### 2. Open in Android Studio

1. Launch Android Studio
2. Select "Open an Existing Project"
3. Navigate to the cloned repository
4. Wait for Gradle sync to complete

### 3. Verify Gradle Configuration

The project uses Gradle Kotlin DSL. Ensure the following files are present:
- `build.gradle.kts` (root)
- `app/build.gradle.kts`
- `settings.gradle.kts`
- `gradle.properties`

### 4. Build the Project

```bash
# Using Gradle wrapper (recommended)
./gradlew build

# Or in Android Studio
Build > Make Project
```

### 5. Run on Device/Emulator

```bash
# Install and run
./gradlew installDebug

# Or use Android Studio's Run button
```

## ⚙️ Configuration

### API Endpoints

The application connects to two backend services configured in `RetrofitInstance.kt`:

- **Main API**: `https://api.educai.xyz`
- **AI Services**: `https://ai.educai.xyz`

### Network Security

The app includes a network security configuration (`network_security_config.xml`) to handle HTTPS connections securely.

### ProGuard Rules

For release builds, ProGuard rules are defined in `app/proguard-rules.pro` to optimize and obfuscate the code.

### Build Types

- **Debug**: Development build with logging enabled
- **Release**: Production build with minification (currently disabled by default)

```kotlin
buildTypes {
    release {
        isMinifyEnabled = false
        proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro"
        )
    }
}
```

## 💻 Usage

### Running the Application

1. **Launch the app** on your device or emulator
2. **Login Screen**: Enter your credentials
   - The app will validate against the Educ.AI backend
   - Successful login stores encrypted tokens locally
3. **Home Screen**: View all your classrooms
   - Click on any classroom to see details
4. **Navigate using**:
   - Bottom navigation bar (main features)
   - Drawer menu (additional features)

### Main Features Usage

#### Creating Educational Materials

1. Open the drawer menu
2. Select "Criar Material" (Create Material)
3. Choose your input source:
   - Enter a YouTube link
   - Upload an audio file
   - Upload a document
   - Provide text instructions
4. Select AI provider (OpenAI)
5. Click generate to create materials

#### Chatting with Edu (AI Assistant)

1. Open drawer menu
2. Select "Fale com Edu"
3. Type your question or tap the microphone for voice input
4. View AI responses in real-time
5. Scroll through conversation history

#### Managing Activities

1. Navigate to a classroom
2. Go to "Atividades" tab
3. View all activities with their status
4. Click on an activity to:
   - Start (if pending)
   - Review (if completed)
   - View details (deadline, questions, etc.)

#### Viewing Leaderboard

1. Open a classroom
2. Navigate to "Leaderboard" tab
3. View ranked students with their scores
4. See profile pictures and points

## 📁 Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/educai/
│   │   │   ├── MainActivity.kt          # Entry point
│   │   │   ├── components/              # Reusable UI components
│   │   │   │   ├── BottomNavigationBar.kt
│   │   │   │   ├── CardAtividade.kt
│   │   │   │   ├── Class.kt
│   │   │   │   ├── DefaultButton.kt
│   │   │   │   ├── DictionaryModal.kt
│   │   │   │   ├── DrawerMenu.kt
│   │   │   │   ├── LoginDrawer.kt
│   │   │   │   ├── LoginForm.kt
│   │   │   │   ├── Post.kt
│   │   │   │   ├── Question.kt
│   │   │   │   ├── StudentRanking.kt
│   │   │   │   └── TopBar.kt
│   │   │   ├── data/
│   │   │   │   ├── contexts/            # Shared contexts
│   │   │   │   │   └── TokenManager.kt  # Token storage & management
│   │   │   │   ├── model/               # Data models
│   │   │   │   │   ├── AnsweredQuestion.kt
│   │   │   │   │   ├── Classroom.kt
│   │   │   │   │   ├── Classwork.kt
│   │   │   │   │   ├── ClassworkReview.kt
│   │   │   │   │   ├── EduResponse.kt
│   │   │   │   │   ├── ErrorResponse.kt
│   │   │   │   │   ├── Leaderboard.kt
│   │   │   │   │   ├── LoginRequest.kt
│   │   │   │   │   ├── Message.kt
│   │   │   │   │   ├── Participant.kt
│   │   │   │   │   ├── Post.kt
│   │   │   │   │   ├── Question.kt
│   │   │   │   │   ├── StudentPicture.kt
│   │   │   │   │   ├── User.kt
│   │   │   │   │   ├── WordDefinition.kt
│   │   │   │   │   └── WordMeaning.kt
│   │   │   │   ├── network/             # Network configuration
│   │   │   │   │   ├── LoggingInterceptor.kt
│   │   │   │   │   └── Retrofit.kt      # API clients setup
│   │   │   │   ├── services/            # API service interfaces
│   │   │   │   │   ├── AuthService.kt
│   │   │   │   │   ├── ClassworkService.kt
│   │   │   │   │   ├── ClassworksService.kt
│   │   │   │   │   ├── DictionaryService.kt
│   │   │   │   │   ├── IAService.kt
│   │   │   │   │   ├── LeaderboardService.kt
│   │   │   │   │   ├── MaterialService.kt
│   │   │   │   │   ├── PostService.kt
│   │   │   │   │   └── UserService.kt
│   │   │   │   └── viewmodel/           # ViewModels
│   │   │   │       ├── AuthViewModel.kt
│   │   │   │       ├── ClassworkViewModel.kt
│   │   │   │       ├── ClassworksViewModel.kt
│   │   │   │       ├── DictionaryViewModel.kt
│   │   │   │       ├── IAViewModel.kt
│   │   │   │       ├── LeaderboardViewModel.kt
│   │   │   │       ├── MaterialViewModel.kt
│   │   │   │       ├── PostViewModel.kt
│   │   │   │       └── UserViewModel.kt
│   │   │   ├── routes/                  # Navigation routes
│   │   │   │   └── DrawerScreens.kt
│   │   │   ├── screens/                 # Screen composables
│   │   │   │   ├── turma/               # Classroom-specific screens
│   │   │   │   │   ├── atividade/
│   │   │   │   │   │   ├── Atividade.kt    # Activity execution
│   │   │   │   │   │   └── Revisao.kt      # Activity review
│   │   │   │   │   ├── Atividades.kt       # Activities list
│   │   │   │   │   ├── Leaderboard.kt      # Rankings
│   │   │   │   │   ├── Pessoas.kt          # Participants
│   │   │   │   │   └── Posts.kt            # Class feed
│   │   │   │   ├── CriarMaterial.kt        # Material creation
│   │   │   │   ├── FaleComEdu.kt           # AI chat
│   │   │   │   ├── Home.kt                 # Home/classrooms
│   │   │   │   ├── Login.kt                # Authentication
│   │   │   │   ├── MainUI.kt               # Main container
│   │   │   │   └── TurmaUI.kt              # Classroom details
│   │   │   ├── ui/theme/                # Theme configuration
│   │   │   │   ├── Color.kt
│   │   │   │   ├── Theme.kt
│   │   │   │   └── Type.kt
│   │   │   └── utils/                   # Utility functions
│   │   │       └── DataTransform.kt
│   │   ├── res/                         # Resources
│   │   │   ├── drawable/                # Icons and images
│   │   │   ├── font/                    # Custom fonts (Montserrat)
│   │   │   ├── values/                  # Strings, colors, themes
│   │   │   └── xml/                     # XML configurations
│   │   └── AndroidManifest.xml          # App manifest
│   ├── androidTest/                     # Instrumented tests
│   └── test/                            # Unit tests
├── build.gradle.kts                     # App-level build config
└── proguard-rules.pro                   # ProGuard rules
```

## 🔌 API Integration

### Authentication Endpoints

```kotlin
// Login
POST /user/auth
Body: { email: String, password: String }
Response: { token: String, refreshToken: String, user: User }

// Refresh Token
POST /user/refreshToken
Header: Cookie: refreshToken
Response: { token: String }

// Logout
POST /user/logoff
```

### User Endpoints

```kotlin
// Get User Profile
GET /user/profile

// Get User Classrooms
GET /user/classrooms

// Get Classroom Participants
GET /classroom/{id}/participants

// Update Profile Picture
POST /user/picture
```

### Classroom Endpoints

```kotlin
// Get Classroom Posts
GET /classroom/{id}/posts

// Get Classroom Activities
GET /classroom/{id}/classworks

// Get Leaderboard
GET /classroom/{id}/leaderboard
```

### Activity Endpoints

```kotlin
// Get Activity Details
GET /classwork/{id}

// Submit Activity
POST /classwork/{id}/submit
Body: { answers: List<AnsweredQuestion> }

// Get Activity Review
GET /classwork/{id}/review
```

### AI Endpoints

```kotlin
// Chat with Edu
POST /chat (AI service)
Body: { message: String, history: List<Message> }
Response: { response: String }

// Generate Educational Material
POST /generate-educational-resource (AI service)
Multipart: instructions, youtubeLink, audio, document
Query: openai: Boolean
Response: Generated material data
```

### Dictionary Endpoint

```kotlin
// Search Word
GET /dictionary/search
Query: word: String
Response: List<WordDefinition>
```

## 🧪 Development

### Running Tests

```bash
# Unit tests
./gradlew test

# Instrumented tests
./gradlew connectedAndroidTest

# Run specific test class
./gradlew test --tests "com.example.educai.YourTestClass"
```

### Code Style

The project follows Kotlin coding conventions:
- Use meaningful variable and function names
- Follow MVVM architecture patterns
- Keep composables small and focused
- Use LiveData for reactive state management
- Implement proper error handling

### Building for Release

```bash
# Build release APK
./gradlew assembleRelease

# Build release AAB (for Play Store)
./gradlew bundleRelease
```

The output files will be in:
- APK: `app/build/outputs/apk/release/`
- AAB: `app/build/outputs/bundle/release/`

### Debugging

Enable logging by checking the LoggingInterceptor in the network layer. Network requests and responses are logged in debug builds.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

```
MIT License

Copyright (c) 2024 educ.ai

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

## 🤝 Contributing

We welcome contributions! Here's how you can help:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

Please ensure your code follows the project's coding standards and includes appropriate tests.

## 📧 Contact & Support

- **Organization**: [educ-ai-org](https://github.com/educ-ai-org)
- **Repository**: [educai-app](https://github.com/educ-ai-org/educai-app)

For bugs and feature requests, please open an issue on GitHub.

---

**Built with ❤️ by the Educ.AI team**
