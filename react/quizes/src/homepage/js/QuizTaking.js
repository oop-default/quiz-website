import React,{Component} from 'react';
import '../css/QuizTaking.css';
import '../css/Arrows.css';
import {changeTable} from './functions';

const myTakenQuizes = [
    {id: 1, quizName: "pirveli", points: 86},
    {id: 2, quizName: "meore", points: 45},
    {id: 3, quizName: "mesame", points: 1},
    {id: 4, quizName: "pirveli", points: 86},
    {id: 5, quizName: "meore", points: 45},
];

const friendsTakenQuizes = [
{quizId: 1, friendId: 1, friend:"vigaca", quizName: "pirveli", points: 123},
{quizId: 2, friendId: 2, friend:"vigacam", quizName: "meore", points: 512413},
{quizId: 3, friendId: 3, friend:"vigacas", quizName: "mesame", points: 123}
];


class QuizTaking extends Component{
    constructor() {
        super();
        this.state = {
            nOfTable : 0,
            friendsTakenQuizes: friendsTakenQuizes,
            myTakenQuizes: myTakenQuizes
        }
    }

    componentDidMount() {
        fetch('http://localhost:8080/ServletFriendsQuizActivity?id=' + 1)
          .then((response) => {
              response.json().then((data) => {
                this.setState({friendsTakenQuizes: data});
              })
            });
    }

    render() {
        return (
            <div >
                <table className="takenQuizes">
                    <thead >
                        <tr>
                            <th colSpan="4">
                                <i onClick = {()=>changeTable(this, this.state.nOfTable)} className="leftArrow"></i>
                                {this.state.nOfTable === 0 ? "my taken quizes" : "friends' taken quizes"}
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
                                    <th>Points</th>
                                </tr>) :
                                (<tr>
                                    <th>#</th>
                                    <th>Friend</th>
                                    <th>QuizName</th>
                                    <th>Points</th>
                                </tr>)
                        }
                    </thead>
                    <tbody>
                        {
                        this.state.nOfTable === 0 ? 
                            (this.state.myTakenQuizes.map((quiz) => {
                                return <tr key={quiz.id}>
                                    <td><a href={"/quiz/:quizId" + quiz.id}>{quiz.id}</a></td>
                                    <td><a href={"/quiz/:quizId" + quiz.id}>{quiz.quizName}</a></td>
                                    <td>{quiz.points}</td>
                                </tr>
                            }))
                        :
                            (this.state.friendsTakenQuizes.map((quiz) => {
                                return <tr key={quiz.quizId}>
                                    <td><a href={"/quiz/:quizId" + quiz.quizId}>{quiz.quizId}</a></td>
                                    <td><a href={"/profile/:userId" + quiz.friendId}>{quiz.friend}</a></td>
                                    <td><a href={"/quiz/:quizId" + quiz.quizId}>{quiz.quizName}</a></td>
                                    <td>{quiz.points}</td>
                                </tr>
                            }))
                        }
                    </tbody>
                </table>
            </div>
        );
    }

}


export default QuizTaking;