### Drag and Drop ###

### Tools and Languages ###

* Language - Android Kotlin
* IDE - Android Studio 3.1

### Purpose ###

* Easy to adapt drag and drop component in your project

### Use Customized Component ###

* Drag and Drop

### Component Provide ###

* Explore your innovation 
* Correct and Wrong answers differentiate by color
* Submit Answer
* Clear Selection
* Reveal Answer

### How to use ###
 * gradle 
```
 implementation 'com.github.arivista-digital:drag-drop:V1_0'
 ```
 * Add jitpack in your project build gradle
```
 maven { url 'https://jitpack.io' }
 ```
 * activity_main Layout initialize
```
    <arivista.lib.dragdrop.CustomView
        android:id="@+id/customView"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    </arivista.lib.dragdrop.CustomView>
```
* MainActivity Funtionalites

* Custom view object creation
```
  val customView = findViewById<CustomView>(R.id.customView)
```

* Add the Image and drawable position
```
  val data=ArrayList<dragmodel>()
        data.add(Optionmodel("14th Jan", 900, 300, 320, 80))
        data.add(Optionmodel("15th Aug", 900, 100, 320, 80))
        data.add(Optionmodel("25th Dec", 900, 500, 320, 80))
        data.add(Optionmodel("12 May", 900, 1100, 320, 80))
        data.add(Optionmodel("14th Feb", 900, 700, 320, 80))
        data.add(Optionmodel("4 Aug", 900, 900, 320, 80))
        customView.setInput(data)

```
* Set Image just call the fuctions on custom view
```
   customView.setImage(R.drawable.demoimage)
```    
### Gif ###
![Demo Gif](/app/screenshot/demo.gif)
### Links ###
* [Arivista ](https://www.arivistadigital.org/ "Arivista ")

### License ###

* Copyright 2018 The Android Open Source Project, Inc.
* Arivista Digital Pvt Ltd

### Contacts ###

* Arivista Digital Pvt Ltd
* Android Team
