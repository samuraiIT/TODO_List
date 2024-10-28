import {createAsyncThunk, createSlice} from "@reduxjs/toolkit";
import {AnyAction} from "redux";
import uuid from "uuid-random";
import filter from "lodash/filter";
import {Item} from "../types";
import {Api} from "../../../Api";

const hasPrefix = (action: AnyAction, prefix: string) => action.type.startsWith(prefix);
const isPending = (action: AnyAction) => action.type.endsWith("/pending");
const isRejected = (action: AnyAction) => action.type.endsWith("/rejected");
const isRejectedAction = (prefix: string) => (action: AnyAction): action is AnyAction => {
    return hasPrefix(action, prefix) && isRejected(action);
};

const initialState = {
    items: [] as Item[]
};

const loadItems = createAsyncThunk(
    "items/all",
    async (param, thunkAPI) => {
        return await Api.loadItems()
            .then(response => {
                if (response.ok) {
                    return response.json()
                } else {
                    return thunkAPI.rejectWithValue(response.json())
                }
            })
    }
);

const addNewItem = createAsyncThunk(
    "items/add-new",
    async (text: string, thunkAPI) => {
        const item = {text: text, uid: uuid()}
        return await Api.addNewItem(item)
            .then(response => {
                if (response.ok) {
                    return item
                } else
                    return thunkAPI.rejectWithValue(response.json())
            })
    }
);

const removeItem = createAsyncThunk(
    "items/remove",
    async (uid: string, thunkAPI) => {
        return await Api.removeItem(uid)
            .then(response => {
                if (response.ok) {
                    return uid
                } else
                    return thunkAPI.rejectWithValue(response.json())
            })
    }
);

export const slice = createSlice({
    name: `ItemReducer`,
    initialState,
    reducers: {},
    extraReducers: builder => {
        builder
            .addCase(loadItems.fulfilled, (state, action) => {
                state.items = action.payload;
            })
            .addCase(addNewItem.fulfilled, (state, action) => {
                state.items.push(action.payload)
            })
            .addCase(removeItem.fulfilled, (state, action) => {
                state.items = filter(state.items, (item) => item.uid !== action.payload)
            })
            .addMatcher(isRejectedAction("items"), (state, action) => {
                console.error("Error response: ", action.payload)
            })
    },
});

export const asyncActions = {
    loadItems,
    addNewItem,
    removeItem
};

export default slice.reducer;
