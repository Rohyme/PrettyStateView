# PrettyStateView

[![](https://jitpack.io/v/Rohyme/PrettyStateView.svg)](https://jitpack.io/#Rohyme/PrettyStateView)  [![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-PrettyStateView-green.svg?style=flat )]( https://android-arsenal.com/details/1/7299 )


PrettyStateView is a light library to set view states such as Loading , Error , Empty or even Custom views you make with just one line without using any XMLs


<img src="https://github.com/Tripl3Dev/PrettyStateView/blob/master/states.gif">


# Installation
- #### Step 1. Add the JitPack repository to your build file 



```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
 ```
 
- #### Step 2. Add the dependency
 ```
 	dependencies {
	        implementation 'com.github.Tripl3Dev:PrettyStateView:2.2.1'
	}

  ```
  
  
  # Usage 
-  For using it in java [HERE](https://github.com/Rohyme/PrettyStateView/blob/master/javaReadme.md) 
	
-  Intialize the StatesConfigFactory singelton 
 
    ```kotlin
    // App class onCreate
     override fun onCreate() {
        super.onCreate()
        StatesConfigFactory.intialize().initDefaultViews()
        }
    ```
- You should initialize default views or just the normal view  :
	- Use ``` initDefaultViews()``` for intialize Normal , Error , Loading and Empty default layouts comming with the lib    
	- Use ```initViews()``` to intialize just Normal View 
> You must use one of them after intializing StateConfigFactory
  
- You can init your own layouts by using 
     ```kotlin
      StatesConfigFactory.intialize()
      	    .initViews()
            .setDefaultEmptyView(R.layout.empty_view)
            .setDefaultErrorView(R.layout.error_view)
            .setDefaultLoadingView(R.layout.loading_view)
     ```
 
 - Add your custom view while intializing StateFactory 
 
   ```kotlin 
   StatesConfigFactory.intialize().addStateView(YOUR_CUSTOM_STATE_VIEW_INTEGER_CONSTANT,R.layout.your_custom_layout) 
   ```

- Add your custome view later in your activity or fragment 

    ```kotlin
    StatesConfigFactory.get().addStateView(YOUR_CUSTOM_STATE_VIEW_INTEGER_CONSTANT,R.layout.your_custom_layout)
    ```

- Finally you can use set The stateView to any view you want just with
 
    ```kotlin
    yourView.setState(StatesConstants.EMPTY_STATE)
    ```
    
    ```kotlin
    yourView.setState(StatesConstants.LOADING_STATE)
    ```
    
    ```kotlin
    yourView.setState(StatesConstants.ERROR_STATE)
    ```
    
    ```kotlin
    yourView.setState(YOUR_CUSTOM_STATE_VIEW_INTEGER_CONSTANT)
    ```     

- You can go back to the content states (your original state) with 
  
    ```kotlin
    yourView.setState(StatesConstants.NORMAL_STATE)
    ```

- You can get view back to make any action on it 
 ```kotlin 
   var stateView =  SetState(StateConstantType : Integer) : View
   stateView.setOnClickListener{
    Toast.makeText(this,"On Whole view clicked do ....",Toast.LENGTH_SHORT).show()
   }
   
   // Or find specific view and do action on it 
   stateView.findViewById<TextView>(R.id.textError).apply {
            text = "Error Message"		 // Set Your error text 
            // set on click listener to the view
	    setOnClickListener {
                Toast.makeText(this@MainActivity, "view error state clicked", Toast.LENGTH_SHORT).show()
            }
        }
 ```


# License
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
