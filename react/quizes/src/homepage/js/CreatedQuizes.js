import React,{Component} from 'react';
import '../css/CreatedQuizes.css'
import {changeTable} from './functions'


const myCreatedQuizes = [
    {id: 1, quizName: "pirveli"},
    {id: 2, quizName: "meore"},
    {id: 3, quizName: "mesame"},
    {id: 4, quizName: "meotxe"},
];

const friendsCreatedQuizes = [
    {id: 1, frinedId:1, friend: "vigaca", quizName: "pirveli"},
    {id: 2, frinedId:2, friend: "vigacam", quizName: "meore"},
    {id: 3, frinedId:3, friend: "vigacas", quizName: "mesame"},
    {id: 4, frinedId:4, friend: "vigacis", quizName: "meotxe"},
    {id: 5, frinedId:5, friend: "vigacit", quizName: "mexute"},
];

class CreatedQuizes extends Component{
    constructor() {
        super();
        this.state = {
            nOfTable : 0,
            myCreatedQuizes: myCreatedQuizes,
            friendsCreatedQuizes: friendsCreatedQuizes
        }
    }

    render() {
        return (
            <div>
                <table className="createdQuizes">
                    <thead >
                        <tr>
                            <th colSpan="3">
                                <i onClick = {()=>changeTable(this, this.state.nOfTable)} className="leftArrow"></i>
                                {this.state.nOfTable === 0 ? "my created quizes" : "friends' created quizes"}
                                <i onClick = {()=>changeTable(this, this.state.nOfTable)}  className="rightArrow"></i>
                            </th>
                        </tr>
                    </thead>
                    <thead>
                        {
                            this.state.nOfTable === 0 ? 
                                (<tr>
                                    <th>#</th>
                                    <th>QuizName</th>
                                </tr>) :
                                (<tr>
                                    <th>#</th>
                                    <th>Friend</th>
                                    <th>QuizName</th>
                                </tr>)
                        }
                    </thead>
                    <tbody>
                        {
                        this.state.nOfTable === 0 ? 
                            (this.state.myCreatedQuizes.map((quiz) => {
                                return <tr key={quiz.id}>
                                    <td><a href={"/quizzes?id=" + quiz.id}>{quiz.id}</a></td>
                                    <td><a href={"/quizzes?id=" + quiz.id}>{quiz.quizName}</a></td>
                                </tr>
                            }))
                        :
                            (this.state.friendsCreatedQuizes.map((quiz) => {
                                return <tr key={quiz.id}>
                                    <td><a href={"/quizzes?id=" + quiz.id}>{quiz.id}</a></td>
                                    <td><a href={"/profile?id=" + quiz.frinedId}>{quiz.friend}</a></td>
                                    <td><a href={"/quizzes?id=" + quiz.id}>{quiz.quizName}</a></td>
                                </tr>
                            }))
                        }
                    </tbody>
                </table>
            </div>
        );
    }
}


export default CreatedQuizes;