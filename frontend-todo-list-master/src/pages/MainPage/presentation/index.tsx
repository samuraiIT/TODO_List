import React, {FC, useEffect} from "react";
import {Item} from "../types";
import NewItem from "../../../components/NewItem";
import TodoItem from "../../../components/TodoItem";

type ItemList = {
    items: Item[],
    createNewItem: Function
    removeItem: Function
    loadItems: Function
}

const Presentation: FC<ItemList> = ({items, createNewItem, removeItem, loadItems}) => {
    useEffect(() => {
        loadItems()
    }, []);
    return (
        <div className="container-fluid vh-100">
            <NewItem createNewItem={createNewItem}/>
            {items.map(item => <TodoItem key={item.uid} item={item} removeItem={removeItem}/>)}
        </div>
    );
}

export default Presentation;