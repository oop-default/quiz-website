import React,{Component} from 'react';
import Achievements from './Achievements'
import '../css/rightPane.css'


class RightPane extends Component {

    render() {
        return (
            <div className ="rightpane">
                <Achievements></Achievements>
            </div>
        );
    }
}

export default RightPane;
