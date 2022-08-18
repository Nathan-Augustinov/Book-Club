import {configureStore} from '@reduxjs/toolkit';
import userReducer from "./userReducer";

const store = configureStore({
    reducer: {
        user: userReducer
    },
    devTools: window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
  });

  export default store;