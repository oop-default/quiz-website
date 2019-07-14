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

class Achievements extends Component{
    constructor() {
        super();
        this.state = {
            nOfTable: 0,
            myAchieves: myAchieves,
            friendsAchieves: []
        }
    }

    componentDidMount() {
        fetch('http://localhost:8080/ServletFriendsAchievements?id=1')
            .then((response) => {
                response.json().then((data) => {
                    this.setState({friendsAchieves: data});
                })
            });
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
                                    <td><a href={'/profile/' + achievement.id}>{achievement.user}</a></td>
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