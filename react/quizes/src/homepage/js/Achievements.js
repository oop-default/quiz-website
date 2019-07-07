import React,{Component} from 'react';
import '../css/Achievements.css'
import {changeTable} from './functions'

const myAchieves = [
    {achievement: "ragaca"},
    {achievement: "ragaca"},
    {achievement: "ragaca"},
    {achievement: "ragaca"},
    {achievement: "ragaca"},
    {achievement: "ragaca"},
    {achievement: "ragaca"},
    {achievement: "ragaca"},
    {achievement: "ragaca"},
    {achievement: "ragaca"},
    {achievement: "ragaca"},
    {achievement: "ragaca"},
    {achievement: "ragaca"},
    {achievement: "ragaca"},
    {achievement: "ragaca"},
    {achievement: "ragaca"},
    {achievement: "ragaca"},
    {achievement: "ragaca"},
    {achievement: "ragaca"},
];

const friendsAchieves = [
    {friend: "vigaca", achievement: "ragaca"},
    {friend: "vigaca", achievement: "ragaca"},
    {friend: "vigaca", achievement: "ragaca"},
    {friend: "vigaca", achievement: "ragaca"},
    {friend: "vigaca", achievement: "ragaca"},
    {friend: "vigaca", achievement: "ragaca"},
    {friend: "vigaca", achievement: "ragaca"},
    {friend: "vigaca", achievement: "ragaca"},
    {friend: "vigaca", achievement: "ragaca"},
    {friend: "vigaca", achievement: "ragaca"},
    {friend: "vigaca", achievement: "ragaca"},
    {friend: "vigaca", achievement: "ragaca"},
    {friend: "vigaca", achievement: "ragaca"},
    {friend: "vigaca", achievement: "ragaca"},
    {friend: "vigaca", achievement: "ragaca"},
    {friend: "vigaca", achievement: "ragaca"},
    {friend: "vigaca", achievement: "ragaca"},
    {friend: "vigaca", achievement: "ragaca"},
    {friend: "vigaca", achievement: "ragaca"},
];

class Achievements extends Component{
    constructor() {
        super();
        this.state = {
            nOfTable: 0,
            myAchieves: myAchieves,
            friendsAchieves: friendsAchieves
        }
    }

    render() {
        return (
            <div>
                <table className="achievements">
                    <thead >
                        <tr>
                            <th colSpan="2">
                                <i onClick = {()=>changeTable(this, this.state.nOfTable)} className="leftArrow"></i>
                                {this.state.nOfTable === 0 ? "my achievements" : "friends' achievements"}
                                <i onClick = {()=>changeTable(this, this.state.nOfTable)}  className="rightArrow"></i>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                        this.state.nOfTable === 0 ? 
                            (this.state.myAchieves.map((achievements) => {
                                return <tr>
                                    <td><a href="https://www.facebook.com">{achievements.achievement}</a></td>
                                </tr>
                            }))
                        :
                            (this.state.friendsAchieves.map((achievements) => {
                                return <tr>
                                    <td><a href="https://www.facebook.com">{achievements.friend}</a></td>
                                    <td><a href="https://www.facebook.com">{achievements.achievement}</a></td>
                                </tr>
                            }))
                        }
                    </tbody>
                </table>
            </div>
        );
    }
}


export default Achievements;