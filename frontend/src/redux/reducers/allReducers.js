import loggedReducer from "./userReducer";

import { combineReducers } from "@reduxjs/toolkit";

const allReducers = combineReducers({
    LoggedInUserDetails: loggedReducer
});

export default allReducers;