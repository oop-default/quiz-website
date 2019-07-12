import React,{Component} from 'react';
import '../css/acceptOrDenie.css';

class AcceptOrDenie extends Component{



    render() {
        return (
            <div id="notHide" display="flex">
                <button id="notHide" className="accept">
                    <span id="notHide" style = {{color:"white"}}>&#10003;</span>
                </button>
                <button id="notHide" className="reject">
                    <span id="notHide" style = {{color:"white"}}>&#10006;</span>
                </button>
            </div>
        );
    }



}

export default AcceptOrDenie;






