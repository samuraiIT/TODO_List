import React, {FC} from "react";
import Presentation from "../presentation";
import {useSelector} from "react-redux";
import {RootState} from "../../../store";
import {MainPage} from "../index"
import {useActions} from "../../../utils";

const Container: FC = () => {
    const {addNewItem, removeItem, loadItems} = useActions(MainPage.Actions)
    const items = useSelector((state: RootState) => state.items)
    return (
        <Presentation
            items={items}
            createNewItem={addNewItem}
            removeItem={removeItem}
            loadItems={loadItems}/>
    )
}

export default Container;