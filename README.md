<p align="center">
    <img src="https://github.com/user-attachments/assets/31c7911a-33a6-48ed-8c3e-4ee0fbffce41" width="350" alt="Logo"/>
</p>

<p align="center">
    <a href="https://android-arsenal.com/api?level=21"><img src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat" alt="API"/></a>
    <a href="https://github.com/megatilus/crouton-library/blob/main/LICENSE"><img src="https://img.shields.io/badge/License-ISC-blue.svg" alt="License"/></a>
    <img src="https://img.shields.io/badge/Size-80kB-orange" alt="Size"/>
    <a href="https://ko-fi.com/nutilus"><img src="https://img.shields.io/badge/Support_me_on_Ko--fi-FF5E5B?logo=ko-fi&logoColor=white" alt="Ko-fi"/></a>
</p>

<p align="center">
  <a href="#overview">Overview</a> ▪︎
  <a href="#preview">Preview</a> ▪︎
  <a href="#installation">Installation</a> ▪︎
  <a href="#usage">Usage</a> ▪︎
  <a href="#custom-crouton">Custom Crouton</a> ▪︎
  <a href="#custom-type">Custom Type</a> ▪︎
  <a href="#default-animations">Default Animations</a> ▪︎
  <a href="#contribute">Contribute</a> ▪
  <a href="#support-me">Support me</a>
</p>

### Crouton is a lightweight library for adding customized Toasts in Jetpack Compose. It offers predefined styles and easy customization. Modern, fast, and flexible.

## Overview
Crouton was created to address a need in modern interface development: elegant and customizable Toasts for Jetpack Compose. Before Crouton, there was a notable lack of libraries offering notifications that combine modernity and flexibility. This library aims to fill that gap by providing attractive and easily customizable Toasts, enhancing user interaction with impactful visual messages well integrated into the application's experience.

### Components:
- `ColoredCrouton` : A color-rich Toast that adds a vibrant and dynamic touch to your notifications.
- `LightCrouton` : A Toast designed for light-themed UIs, offering seamless integration into bright environments.
- `DarkCrouton` : A Toast tailored for dark-themed UIs, ensuring clear and elegant readability in low-light settings.
- `CustomCrouton` : A fully customizable Toast for creating unique designs tailored to your specific needs.

### Types:
Crouton defines several predefined types within the `CroutonType` enum to suit various notification needs:
- **Success** ✅: Displays a green Toast with a success icon, perfect for confirming actions or highlighting positive outcomes.
- **Info** 🔵: Shows a blue Toast with an info icon, ideal for informational messages that provide additional context or instructions.
- **Warning** ⚠️: Presents an orange Toast with a warning icon, useful for cautionary alerts that need to catch the user's attention.
- **Error** ❌: Exhibits a red Toast with an error icon, suitable for error messages or critical notifications that require immediate attention.
- **Custom** 🎨: Fully customizable Toast with user-defined colors and icons. Jump to [section](#custom-type).

These types are available for the following components:
- `ColoredCrouton`
- `LightCrouton`
- `DarkCrouton`

**Note:** `CustomCrouton` is a separate style that allows for complete customization. **For more details, see** [CustomCrouton](#custom-crouton).

### Gravity:
Options of the `CroutonGravity` enum:
 - **TOP**
 - **CENTER**
 - **BOTTOM**

### Duration:
Options of the `CroutonDuration` enum:
- **SHORT** - *2.5 seconds*
- **LONG** - *4 seconds*

## Preview
With Crouton, you can easily customize the appearance by choosing to include or exclude the following elements:
- The border
- The icon
- Message of body
- 
|ColoredCrouton|DarkCrouton|LightCrouton|
|-----------|-----------|-----------|
|<img src="https://github.com/user-attachments/assets/ecb1a10f-6ddc-4e59-8022-1b222e771075" width="250" alt="Colored Crouton"/>|<img src="https://github.com/user-attachments/assets/635c2eed-25c5-4016-be22-3f9bfe8afcb8" width="250" alt="Dark Crouton"/>|<img src="https://github.com/user-attachments/assets/82f5e5a4-f3bf-45fc-9006-0e0d605f4294" width="250" alt="Light Crouton"/>

## Installation
Crouton is available on `mavenCentral()`

```kotlin
dependencies {
    implementation("io.github.megatilus:crouton:1.2.0")
}
```

## Usage

### Common Constructor:
The constructor for `ColoredCrouton`, `DarkCrouton` and `LightCrouton` has the following parameters:
```kotlin
title: String
showCrouton: Boolean,
onCroutonListener: (Boolean) -> Unit,
message: String? = null,
croutonType: CroutonType,
croutonGravity: CroutonGravity = CroutonGravity.BOTTOM,
duration: Long = CroutonDuration.SHORT,
showIcon: Boolean = true,
showBorder: Boolean = true,
enterTransition: EnterTransition = CroutonPosition.getEnterTransition(croutonGravity),
exitTransition: ExitTransition = CroutonPosition.getExitTransition(croutonGravity)
```

- `title`: The title of the Crouton.
- `showCrouton`: A Boolean to control the visibility of the Crouton.
- `onCroutonListener` - Listener triggered when the duration expires. Use it to manage the Crouton’s visibility.
- `message` - Message to display in the Crouton. *(Optional)*
- `croutonType` - Defines the type of Crouton. For types, refer to [this section](#types).
- `croutonGravity` - Positions the Crouton on the screen. Details can be found [here](#gravity). (Default: `CroutonGravity.BOTTOM`)
- `duration` - Display duration. Use a predefined duration, see options [here](#duration) or specify your own. (Default: `CroutonDuration.SHORT`)
- `showIcon`- Shows or hides the icon. (Default: **true**)
- `showBorder`- Shows or hides the border. (Default: **true**)
- `enterTransition`- Specifies the entry transition. (Default: based on `croutonGravity`, see examples [here](#default-animations))
- `exitTransition`- Specifies the exit transition. (Default: based on `croutonGravity`, see examples [here](#default-animations))

### Example:
```kotlin
var show by remember {
 mutableStateOf(false)
}

ColoredCrouton(
    title = "Success",
    message = "Your first Crouton is very beautiful",
    showCrouton = show,
    onCroutonListener = { isDismiss ->
        show = isDismiss
    },
    croutonType = CroutonType.Success
)
```

### Full example:
Simply copy and paste this code to get started quickly.
```kotlin
@Composable
fun MyScreen() {
    var show by remember {
        mutableStateOf(false)
    }

    // The main content of the screen
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        Button(onClick = { show = true }) {
            Text("Show Crouton")
        }
    }

    // Your Crouton
    ColoredCrouton(
        title = "Success",
        showCrouton = show,
        onCroutonListener = { isDismiss ->
            show = isDismiss // false
        },
        croutonType = CroutonType.Success
    )
}
```

## Custom Crouton
```kotlin
fun CustomCrouton(
    title: String,
    showCrouton: Boolean,
    onCroutonListener: (Boolean) -> Unit,
    message: String? = null,
    titleStyle: TextStyle? = null,
    messageStyle: TextStyle? = null,
    croutonGravity: CroutonGravity = CroutonGravity.BOTTOM,
    duration: Long = CroutonDuration.SHORT,
    autoDismiss: Boolean = true,
    fillMaxWidth: Boolean = false,
    sticky: Boolean = false,
    border: BorderStroke? = null,
    shape: RoundedCornerShape = RoundedCornerShape(14.dp),
    backgroundColor: Color = Color.White,
    leadingContent: (@Composable () -> Unit)? = null,
    leadingSpace: Dp = 0.dp,
    trailingContent: (@Composable () -> Unit)? = null,
    trailingSpace: Dp = 0.dp,
    enterTransition: EnterTransition = CroutonPosition.getEnterTransition(croutonGravity),
    exitTransition: ExitTransition = CroutonPosition.getExitTransition(croutonGravity)
)
```
- `title` - The title of the Crouton.
- `showCrouton` - A Boolean to control the visibility of the Crouton.
- `onCroutonListener` - A listener triggered after the `duration` has elapsed. Use it to perform actions after the Crouton is no longer visible. If `autoDismiss` is **false**, this listener will not be called.
- `message` - The message of the Crouton (optional).
- `titleStyle` - The style to be applied to the title (optional).
- `messageStyle` - The style to be applied to the message (optional).
- `croutonGravity` - The position of the Crouton on the screen. Use values from `CroutonGravity`. (Default: `CroutonGravity.BOTTOM`)
- `duration` - The display duration of the Crouton. Refer to `CroutonDuration` for pre-configured durations or use a custom duration. (Default: `CroutonDuration.SHORT`)
- `autoDismiss` - If **true**, the Crouton will automatically dismiss after the duration. If **false**, the Crouton will remain on the screen until manually dismissed. (Default: `true`)
- `fillMaxWidth` - If **true**, the Crouton will occupy the full width of the screen. (Default: `false`)
- `sticky` - If **true**, the Crouton will be fixed at the top or bottom of the screen. (Default: `false`)
- `border` - The border stroke of the Crouton (optional).
- `shape` - The shape of the Crouton. Defaults to a rounded corner shape with a radius of 14.dp. (Default: `RoundedCornerShape(14.dp)`)
- `backgroundColor` - The background color of the Crouton. (Default: `Color.White`)
- `leadingContent` - Optional composable content to display on the left side of the Crouton.
- `leadingSpace` - The space between the leading content and the text content. (Default: `0.dp`)
- `trailingContent` - Optional composable content to display on the right side of the Crouton.
- `trailingSpace` - The space between the trailing content and the text content. (Default: `0.dp`)
- `enterTransition` - The enter animation for the Crouton. By default, it uses an animation based on the `croutonGravity`, see examples [here](#default-animations).
- `exitTransition` - The exit animation for the Crouton. By default, it uses an animation based on the `croutonGravity`, see examples [here](#default-animations).

### Example usage:
Example of creating a persistent Snackbar.
```kotlin
var show by remmeber {
    mutableStateOf(false)
}

CustomCrouton(
    title = "My connection really sucks",
    titleStyle = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
        color = Color.White
    ),
    showCrouton = show,
    onCroutonListener = {},
    sticky = true,
    autoDismiss = false,
    fillMaxWidth = true,
    backgroundColor = Color.DarkGray,
    shape = RoundedCornerShape(0.dp),
    leadingContent = {
        Icon(
            imageVector = Icons.Rounded.Call,
            contentDescription = null,
            tint = Color.White
        )
    },
    leadingSpace = 10.dp,
    trailingContent = {
        TextButton(onClick = { show = false }) {
            Text(text = "OK",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                ) 
            )
        }
    }
)
```

## Custom Type:
The `CroutonType.Custom()` requires three parameters:
```kotlin
CroutonType.Custom(
    primaryColor: Color,
    secondaryColor: Color,
    customIcon: Int
)
```

### Example:
```kotlin
CroutonType.Custom(
    primaryColor = Color.Cyan, 
    secondaryColor = Color.Blue, 
    icon = R.drawable.ic_stars
)
```

See the difference:
|ColoredCrouton|DarkCrouton|LightCrouton|
|-----------|-----------|-----------|
|<img src="https://github.com/user-attachments/assets/146b5229-1099-4d10-a586-1f50bf1c49d6" width="260" alt="Colored Crouton"/>|<img src="https://github.com/user-attachments/assets/9ed1c08e-beba-4bcf-b392-b5e84c096b40" width="250" alt="dark Crouton"/>|<img src="https://github.com/user-attachments/assets/0112fb91-32da-4c6e-a753-a554581ae9c2" width="270" alt="Light Crouton"/>

## Default Animations:
Here are the default animations based on the gravity of the Crouton.

|Top|Center|Bottom|
|-----------|-----------|-----------|
|<img src="https://github.com/user-attachments/assets/b35bb363-8ad1-4c55-8fcb-2831f007a561" width="250" alt="Animation Bottom"/>|<img src="https://github.com/user-attachments/assets/9d92f9e9-a9e1-4fb3-b19a-fb2dde8ed102" width="250" alt="Animation Center"/>|<img src="https://github.com/user-attachments/assets/761154d5-6dc4-41e5-864f-af12a8721922" width="250" alt="Animation Bottom"/>

## Contribute
Thank you for your interest in contributing to our project! Here’s how you can get involved:

### Reporting Issues:
If you find a bug or have an idea for an improvement, please open an [issue](https://github.com/megatilus/crouton-library/issues). Provide as much detail as possible to help us understand and address the problem effectively.

### Submitting a Pull Request:
To propose changes to the project, you can submit a [pull request](https://github.com/megatilus/crouton-library/pulls).

## Support me
- ⭐: If you like this project, please consider starring the repository. It helps others discover it and shows your appreciation for the work put into it.
- ☕ : If you feel extra generous, you can support me by making a donation on [Ko-fi](https://ko-fi.com/nutilus). Every contribution, no matter how small, is greatly appreciated and helps me to continue improving this project.
