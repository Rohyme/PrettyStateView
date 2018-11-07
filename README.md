# PrettyStateView

PrettyStateView is a light library to set view states such As Loading , Error , Empty or even Custom views You make .


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
	        implementation 'com.github.Tripl3Dev:PrettyStateView:2.1.1'
	}

  ```
  
  
  # Usage 
-  Intialize the  StatesConfigFactory singelton 
 
    ```kotlin
    // App class onCreate
     override fun onCreate() {
        super.onCreate()
        StatesConfigFactory.intialize().initDefaultViews()
        }
    ```
- You should initialize default views or just the normal view  :
  use ``` initDefaultViews()``` for intialize Normal , Error , Loading and Empty default layouts comming with the lib    
  use ```initViews()``` to intialize just Normal View 
  
- You can init your own layouts by using 
     ```kotlin
      StatesConfigFactory.intialize()
      	    .initViews()
            .setDefaultEmptyView(R.layout.empty_view)
            .setDefaultErrorView(R.layout.error_view)
            .setDefaultLoadingView(R.layout.loading_view)
     ```
 
 - Add your custom View while intializing stateFactory 
 
   ```kotlin 
   StatesConfigFactory.intialize().addStateView(YOUR_CUSTOM_STATE_VIEW_INTEGER_CONSTANT,R.layout.your_custom_layout) 
   ```

- Add your custome view later in your activity or fragment 

    ```kotlin
    StatesConfigFactory.get().addStateView(YOUR_CUSTOM_STATE_VIEW_INTEGER_CONSTANT,R.layout.your_custom_layout)
    ```

- finally you can use set The stateView to any view you want just with
 
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

    * Note : Your view shouldn't be wrap content (Height or Width)
     

- You can go back to the content states (Your original state) with 
  
    ```kotlin
    yourView.setState(StatesConstants.NORMAL_STATE)
    ```

- You can get view back to make any action on it 
 ```kotlin 
    SetState(StateConstantType : Integer) : View
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
