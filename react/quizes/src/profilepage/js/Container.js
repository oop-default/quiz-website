import React,{Component} from 'react';
import LeftPane from './LeftPane'
import Middle1 from './Middle1'
import Middle2 from './Middle2'
import RightPane from './RightPane'
import '../css/container.css'



class Container extends Component {
    constructor() {
        super();
        // console.log("in Container do something")
    }

    render() {
        return (
            <div className="container">
                <LeftPane></LeftPane>
                <Middle1></Middle1>
                <Middle2></Middle2>
                <RightPane></RightPane>
            </div>
        );
    }
}

export default Container;
