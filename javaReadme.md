 # Usage 
-  Intialize the StatesConfigFactory singelton 
 
    ```java
    // App class onCreate
     @override
    public void onCreate() {
        super.onCreate();
        StatesConfigFactory.Companion.intialize().initDefaultViews();
        }
    ```
- You should initialize default views or just the normal view  :
	- Use ``` initDefaultViews()``` for intialize Normal , Error , Loading and Empty default layouts comming with the lib    
	- Use ```initViews()``` to intialize just Normal View 
> You must use one of them after intializing StateConfigFactory
  
- You can init your own layouts by using 
     ```java
      StatesConfigFactory.Companion.intialize()
      	    .initViews()
            .setDefaultEmptyView(R.layout.empty_view)
            .setDefaultErrorView(R.layout.error_view)
            .setDefaultLoadingView(R.layout.loading_view);
     ```
 
 - Add your custom view while intializing StateFactory 
 
   ```java 
   StatesConfigFactory.Companion.intialize().addStateView(YOUR_CUSTOM_STATE_VIEW_INTEGER_CONSTANT,R.layout.your_custom_layout) ;
   ```

- Add your custome view later in your activity or fragment 

    ```java
    StatesConfigFactory.Companion.get().addStateView(YOUR_CUSTOM_STATE_VIEW_INTEGER_CONSTANT,R.layout.your_custom_layout);
    ```

- Finally you can use set The stateView to any view you want just with
 
    ```java
    StateExecuterKt.setState(yourView,StatesConstants.EMPTY_STATE);
    ```
    
    ```java
    StateExecuterKt.setState(yourView,StatesConstants.LOADING_STATE);
    ```
    
    ```java
    StateExecuterKt.setState(yourView,StatesConstants.ERROR_STATE);
    ```
    
    ```java
    StateExecuterKt.setState(yourView,YOUR_CUSTOM_STATE_VIEW_INTEGER_CONSTANT);
    ```     

- You can go back to the content states (your original state) with 
  
    ```java
    StateExecuterKt.setState(yourView,StatesConstants.NORMAL_STATE);
    ```

- You can get view back to make any action on it 
 ```java 
   View stateView =  SetState(Int StateConstantType);
  
   stateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Toast.makeText(this,"On Whole view clicked do ....",Toast.LENGTH_SHORT).show()                
            }
        });
   
   // Or find specific view and do action on it 
   TextView errotTxt = (TextView)v.findViewById(R.id.textError);
   errorTxt.setText("Error Message");  // Set Your error text
   // set on click listener to the view
	    errorTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Toast.makeText(this,"On Whole view clicked do ....",Toast.LENGTH_SHORT).show()                
            }
        });
      
 ```
