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
### Project level import ###

* build.gradle
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
 ```
### Module level import ###

* build.gradle
```
dependencies {

     implementation 'com.github.arivista-digital:drag-drop:v-0.2'
     
	}
 ```
 * activity_main Layout initialize
```
    <arivista.lib.dragdrop.CustomView
        android:id="@+id/customView"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    </arivista.lib.dragdrop.CustomView>
```
### MainActivity Funtionalites ###

* Custom view object creation
```
  val customView = findViewById<CustomView>(R.id.customView)
```

* Add the Image and drawable position
```
  val data=ArrayList<dragmodel>()
        data.add(Optionmodel("12th Feb", 136, 72, 380, 35))
        data.add(Optionmodel("15th Aug", 136, 108, 380, 35))
        data.add(Optionmodel("25th Dec", 136, 144, 380, 35))
        data.add(Optionmodel("12 May", 136, 181, 380, 35))
        data.add(Optionmodel("14th Feb", 136, 218, 380, 35))
        customView.setInput(data,R.drawable.xx)
```    
### Gif ###
![Alt Text](/app/screenshot/demo.gif)
### Links ###
* [Arivista ](https://www.arivistadigital.org/ "Arivista ")

### License ###

* Copyright 2018 The Android Open Source Project, Inc.
* Arivista Digital Pvt Ltd

### Contacts ###

* Arivista Digital Pvt Ltd
* Android Team
