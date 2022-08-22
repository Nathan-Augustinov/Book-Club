import { createSlice } from "@reduxjs/toolkit"
import update from 'immutability-helper';
const yourBooksReducer = createSlice({
    name: 'yourBooks',
    initialState: [],
    reducers:{
        addYourBooks: (state, action) => {
            return update(state, {$set: action.payload});
        }
    }
})

export const {addYourBooks} = yourBooksReducer.actions;
export default yourBooksReducer.reducer;