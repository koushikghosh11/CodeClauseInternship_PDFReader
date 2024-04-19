## PDF Reader App

This Android application allows users to read PDF files stored on their device. Developed for a CodeClause internship, this project utilizes Java for development and leverages external libraries for functionalities.

**Features:**

* Reads PDF documents from local storage.
* Implements runtime permissions for storage access using Dexter library.
* Renders PDFs using the AndroidPdfViewer library by barteksc.

**Libraries:**

* Dexter: [https://github.com/Karumi/Dexter](https://github.com/Karumi/Dexter)
* AndroidPdfViewer: [https://github.com/barteksc/AndroidPdfViewer](https://github.com/barteksc/AndroidPdfViewer)

**Getting Started**

1. Clone this repository.
2. Ensure you have the required libraries set up in your project's `build.gradle` file.
   - Dexter
   - AndroidPdfViewer (refer to library documentation for specific version and implementation instructions)
3. Import the project into your Android Studio environment.
4. Run the application on an Android device or emulator.

**Permissions:**

The app requests storage permission to access local PDF files. This permission is handled using Dexter library.

**Disclaimer:**

This is a basic project intended for educational purposes. It may require further development for production use.

**CodeClause Internship**
