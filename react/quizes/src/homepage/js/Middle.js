import React,{Component} from 'react';
import '../css/Middle.css'
import Left from './Left'
import Anouncements from './Anouncements'
import Friends from './Friends'


class Middle extends Component {

    render() {
        return (
            <div className ="middle">
                <Left></Left>
                <Anouncements></Anouncements>
                <Friends></Friends>
            </div>
        );
    }

}

export default Middle;
