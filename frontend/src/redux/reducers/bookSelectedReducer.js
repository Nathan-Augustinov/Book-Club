import { createSlice } from "@reduxjs/toolkit"
import update from 'immutability-helper';
const bookSelectedReducer = createSlice({
    name: 'bookSelected',
    initialState: null,
    reducers:{
        bookSelected: (state, action) => {
            // console.log(action.payload);
            return update(state, {$set: action.payload});
        }
    }
})

export const {bookSelected} = bookSelectedReducer.actions;
export default bookSelectedReducer.reducer;