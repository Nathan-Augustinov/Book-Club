import {configureStore} from '@reduxjs/toolkit';
import userReducer from "./userReducer";
import allBooksReducer from './allBooksReducer';
import bookSelectedReducer from './bookSelectedReducer';
import yourBooksReducer from './yourBooksReducer';

const store = configureStore({
    reducer: {
        user: userReducer,
        allBooks: allBooksReducer,
        yourBooks: yourBooksReducer,
        bookSelected: bookSelectedReducer,
    },
    devTools: window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
  });

  export default store;