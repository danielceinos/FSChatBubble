# FSChatBubble

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/danielceinos/FSChatBubble/blob/master/LICENSE.md)
[![Version](https://img.shields.io/badge/jitpack-1.0.0-green.svg)](https://jitpack.io/#danielceinos/FSChatBubble/1.0.0)

<p align="center">
	<img src="https://github.com/danielceinos/FSChatBubble/blob/develop/example.gif" />
</p>

# Requirements

- minAndroidSdk: 16

# Installation
  
  Add to your gradle.build:
  ```
  allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ```
  ```
  dependencies {
	        compile 'com.github.danielceinos:FSChatBubble:1.0.0'
	}
  ```
# Use

## Options

```xml
<declare-styleable name="FSChatBubble">
    <attr format="dimension" name="textSize" />
    <attr format="string" name="text" />
    <attr format="color" name="bubbleColor" />
    <attr format="color" name="textColor" />
    <attr name="bubblePosition">
      <flag name="rightSingle" value="0" />
      <flag name="rightTop" value="1" />
      <flag name="rightMiddle" value="2" />
      <flag name="rightBottom" value="3" />
      <flag name="leftSingle" value="4" />
      <flag name="leftTop" value="5" />
      <flag name="leftMiddle" value="6" />
      <flag name="leftBottom" value="7" />
    </attr>
</declare-styleable>
```

## Example

```xml
 <com.fireshield.fschatbubble.FSChatBubble
      android:id="@+id/bubble_chat"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:layout_marginLeft="6dp"
      android:layout_marginRight="6dp"
      app:textColor="#FFF"
      app:textSize="18sp"
      app:text="Testgin"
      />
```

```kotlin
	val bubble = bubbleView.findViewById<FSChatBubble>(R.id.bubble_chat)
	bubble.text = content
	bubble.bubbleFSBubblePosition = FSBubblePosition.LeftSingle
```
