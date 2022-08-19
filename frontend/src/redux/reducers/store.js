import {configureStore} from '@reduxjs/toolkit';
import userReducer from "./userReducer";
import allBooksReducer from './allBooksReducer';

const store = configureStore({
    reducer: {
        user: userReducer,
        allBooks: allBooksReducer
    },
    devTools: window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
  });

  export default store;