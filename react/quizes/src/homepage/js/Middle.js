import React,{Component} from 'react';
import '../css/Middle.css'
import Left from './Left'
import Anouncements from './Anouncements'
import Friends from './Friends'
import Activities from './Activities'


class Middle extends Component {

    constructor() {
        super();
        this.state= {
            isAdmin: false
        }
    }

    render() {
        return (
            <div className ="middle">
                <Left isAdmin={this.state.isAdmin}></Left>
                <Anouncements isAdmin={this.state.isAdmin}></Anouncements>
                <Activities></Activities>
                <Friends></Friends>
            </div>
        );
    }

}

export default Middle;
