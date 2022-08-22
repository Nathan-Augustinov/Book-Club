import { createSlice } from "@reduxjs/toolkit"
import update from 'immutability-helper';
const availableBooksReducer = createSlice({
    name: 'availableBooks',
    initialState: [],
    reducers:{
        addAvailableBooks: (state, action) => {
            return update(state, {$set: action.payload});
        }
    }
})

export const {addAvailableBooks} = availableBooksReducer.actions;
export default availableBooksReducer.reducer;