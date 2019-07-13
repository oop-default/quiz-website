import React,{Component} from 'react';
import '../css/Achievements.css'
import {changeTable} from './functions'

const myAchieves = [
    {id: 1, achievement: "ragaca"},
    {id: 2, achievement: "ragaca"},
    {id: 3, achievement: "ragaca"},
    {id: 4, achievement: "ragaca"},
    {id: 5, achievement: "ragaca"},
    {id: 6, achievement: "ragaca"},
    {id: 7, achievement: "ragaca"},
    {id: 8, achievement: "ragaca"},
    {id: 9, achievement: "ragaca"},
    {id: 0, achievement: "ragaca"},
    {id: 10, achievement: "ragaca"},
    {id: 11, achievement: "ragaca"},
    {id: 12, achievement: "ragaca"},
    {id: 13, achievement: "ragaca"},
    {id: 14, achievement: "ragaca"},
    {id: 15, achievement: "ragaca"},
    {id: 16, achievement: "ragaca"},
    {id: 17, achievement: "ragaca"},
    {id: 18, achievement: "ragaca"},
];

const friendsAchieves = [
    {id: 1, friendId: 1, friend: "vigaca", achievement: "ragaca"},
    {id: 2, friendId: 2, friend: "vigaca", achievement: "ragaca"},
    {id: 3, friendId: 3, friend: "vigaca", achievement: "ragaca"},
    {id: 4, friendId: 4, friend: "vigaca", achievement: "ragaca"},
    {id: 5, friendId: 5, friend: "vigaca", achievement: "ragaca"},
    {id: 6, friendId: 6, friend: "vigaca", achievement: "ragaca"},
    {id: 7, friendId: 7, friend: "vigaca", achievement: "ragaca"},
    {id: 8, friendId: 8, friend: "vigaca", achievement: "ragaca"},
    {id: 9, friendId: 9, friend: "vigaca", achievement: "ragaca"},
    {id: 0, friendId: 0, friend: "vigaca", achievement: "ragaca"},
    {id: 10, friendId: 11, friend: "vigaca", achievement: "ragaca"},
    {id: 11, friendId: 12, friend: "vigaca", achievement: "ragaca"},
    {id: 12, friendId: 13, friend: "vigaca", achievement: "ragaca"},
    {id: 13, friendId: 14, friend: "vigaca", achievement: "ragaca"},
    {id: 14, friendId: 15, friend: "vigaca", achievement: "ragaca"},
    {id: 15, friendId: 16, friend: "vigaca", achievement: "ragaca"},
    {id: 16, friendId: 17, friend: "vigaca", achievement: "ragaca"},
    {id: 17, friendId: 18, friend: "vigaca", achievement: "ragaca"},
    {id: 18, friendId: 19, friend: "vigaca", achievement: "ragaca"},
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
                            (this.state.myAchieves.map((achievement, i) => {
                                return <tr key={i}>
                                    <td>{achievement.achievement}</td>
                                </tr>
                            }))
                        :
                            (this.state.friendsAchieves.map((achievement, i) => {
                                return <tr key={i}>
                                    <td><a href={'/profile/' + achievement.friendId}>{achievement.friend}</a></td>
                                    <td>{achievement.achievement}</td>
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