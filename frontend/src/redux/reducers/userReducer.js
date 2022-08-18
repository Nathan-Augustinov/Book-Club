import { createSlice } from "@reduxjs/toolkit"
import update from 'immutability-helper';
const userReducer = createSlice({
    name: 'user',
    initialState: null,
    reducers:{
        loginUser: (state, action) => {
            return update(state, {$set: action.payload});
        },
        updateUser: (state, action) => {
            return update(state, {$set: action.payload});
        },
        logoutUser: (state, action) => {
            return update(state, {$set: null});
        }
    }
})

export const {loginUser, updateUser, logoutUser} = userReducer.actions;
export default userReducer.reducer;
