import React,{Component} from 'react';
import '../css/Middle.css'
import Left from './Left'
import Anouncements from './Anouncements'
import Friends from './Friends'
import Activities from './Activities'


class Middle extends Component {

    render() {
        return (
            <div className ="middle">
                <Left></Left>
                <Anouncements></Anouncements>
                <Activities></Activities>
                <Friends></Friends>
            </div>
        );
    }

}

export default Middle;
