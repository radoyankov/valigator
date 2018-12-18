![logo](https://raw.githubusercontent.com/RadoslavYankov/valigator/master/logo.png)


Validate EditText fields in a couple of lines, with custom, scalable validations.

[![Download](https://img.shields.io/badge/version-1.0-06770b.svg?style=flat-square)](https://bintray.com/radoslav/maven/valigator) [![Twitter URL](https://img.shields.io/badge/twitter-%40Radoslav-1DA1F2.svg?style=flat-square&logo=twitter)](http://twitter.com/Radoslav_Y)


## Usage

Valigator enables you to easily validate EditTexts with custom validations.
Here's how you do that!

First you create your validators. 
The library supports 2 types of validators. One which doesn't have any external dependencies, and one which does. You can use the second type if your validation is dependant on external data.

```kotlin
        val normalValidator = Validator("Optional Error Message") {
          text.length < 5
        }

        val customValidator = Validator.Custom<RadioButton>("Custom Error Message") { button : RadioButton ->
          button?.isChecked == true && text.contains("spaceX")
        }
        
        //Multiple errors in one validation
        val multipleErrorValidator = Validator{
          if (this == null) {
                error = "Null object"
          } else if (text.isEmpty()) {
                error = "Text is empty"
          }
          //it's a good idea to return true, to tell the library you've already displayed the error
          true
        }
        
```

The second kind of validation requires a class type to be specified. This is the type of the external data it will be expecting.

The second step is to enable the validator on EditText fields.

```kotlin
        edit_text.setValidator(normalValidator)

        edit_text_1.setValidator(normalValidator)
        
        edit_text_2.setValidator(customValidator.addDependency(button_dependency))
        
        edit_text_3.setValidator("Simple validation") {
            text.isNotEmpty()
        }
```

**Important**: For external dependancy validations it's required to add a dependency with the `addDependency()` function. Only one dependency is supported, but it can be anything, for example, a list of views.


You can see the third EditText field which accepts a String and a function. That one is a third, extra kind of validation, which doesn't require any premade validators, and can validate fields on the go.


That's it! When you set up the validators, each time the text inside of the EditText field changes, the validation will be preformed.

Here's a preview of those fields:

![preview](https://raw.githubusercontent.com/RadoslavYankov/valigator/master/preview.gif)

---
## Download

### Manually

You can manually download [the library class](https://github.com/RadoslavYankov/valigator/blob/master/valigator/src/main/java/com/radoslav/valigator/Validator.kt) and use it in your application.


### Gradle

```gradle
dependencies {
  implementation 'com.radoslav.valigator:valigator:$latest_version'

}
 ```
 
### Maven
```xml
<dependency>
  <groupId>com.radoslav.valigator</groupId>
  <artifactId>valigator</artifactId>
  <version>latest_version</version>
  <type>pom</type>
</dependency>
```

---
## Compatibility

Minimum Android SDK: API level 19

---
## Author

Radoslav Yankov [@Radoslav_Y](https://twitter.com/Radoslav_Y)

---
## Getting help

If you spot a problem you can open an issue on the Github page, or alternatively, you can tweet me at [@Radoslav_Y](https://twitter.com/Radoslav_Y)

---
## License

FastList is released under the [MIT License](https://github.com/RadoslavYankov/valigator/blob/master/LICENSE).
