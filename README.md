# PhotoBook

PhotoBook is android application, displaying photos from server and save bookmark.

## Prerequisites
- Android SDK v28
- Latest Android Build Tools
- Android Support Repository


## Installation
Clone this repository and import into **Android Studio**
```bash
git clone https://github.com/sovanminea/photobook.git
```

## Configurations
- Base URL of server is stored in build.gradle project level as following:
```
allprojects {
    ...
    ext {
        serverDebugBaseUrl = "\"https://picsum.photos/\""
        serverReleaseBaseUrl = "\"https://picsum.photos/\""
    }
    ...
}
```

## Maintainers
This project is mantained by:
- [Sovanminea Seng](https://github.com/sovanminea)
