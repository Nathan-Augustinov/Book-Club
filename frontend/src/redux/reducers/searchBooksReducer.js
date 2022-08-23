import { createSlice } from "@reduxjs/toolkit"
import update from 'immutability-helper';
const searchBooksReducer = createSlice({
    name: 'searchedBooks',
    initialState: [],
    reducers:{
        addSearchedBooks: (state, action) => {
            return update(state, {$set: action.payload});
        }
    }
})

export const {addSearchedBooks} = searchBooksReducer.actions;
export default searchBooksReducer.reducer;