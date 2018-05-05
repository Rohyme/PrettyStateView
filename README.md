# PrettyStateView

PrettyStateView is a light library to set view states such As Loading , Error , Empty or even Custom views You make .

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
	        implementation 'com.github.Tripl3Dev:PrettyStateView:1.0'
	}
  ```
  
  
  # Usage 
-  Intialize the  StatesConfigFactory singelton 
 
    ```
    // App class onCreate
     override fun onCreate() {
        super.onCreate()
        StatesConfigFactory.intialize().initDefaultViews(this)
        }
    ```
- You can initialize default views for Error , Loading and Empty view comming with the lib 
  by using   ```initDefaultViews(this)```

- You can init your own layouts by using 
     ```
      StatesConfigFactory.intialize()
            .setDefaultEmptyView(EmptyView)
            .setDefaultErrorView(ErrorView)
            .setDefaultLoadingView(LoadingView)
     ```
 
 - Add your custom View while intializing stateFactory 
 
     ``` StatesConfigFactory.intialize().addStateView(YOUR_CUSTOM_STATE_VIEW_INTEGER_CONSTANT,yourCustomView) ```

- Add your custome view later in your activity or fragment 

    ```StatesConfigFactory.get().addStateView(YOUR_CUSTOM_STATE_VIEW_INTEGER_CONSTANT,yourCustomView)```

- finally you can use set The stateView to any view you want just with
 
    ```testingView.setState(StatesConstants.EMPTY_STATE)```
    
    ```testingView.setState(StatesConstants.LOADING_STATE)```
    
    ```testingView.setState(StatesConstants.ERROR_STATE)```
    
    ```testingView.setState(YOUR_CUSTOM_STATE_VIEW_INTEGER_CONSTANT)```

    * Note : Your view shouldn't be wrap content (Height or Width)
     

- You can go back to the content states (Your original state) with 
  
    ```testingView.setState(StatesConstants.NORMAL_STATE)```

- SetState(StateConstantType : Integer) method return the stateView  if u wanna to use the view to make action or anything 

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
