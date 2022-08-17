import loggedReducer from "./isLogged";

import { combineReducers } from "@reduxjs/toolkit";

const allReducers = combineReducers({
    LoggedInUserDetails: loggedReducer
});

export default allReducers;