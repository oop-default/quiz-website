import React,{Component} from 'react';
import '../css/Left.css';
import '../css/Arrows.css';
import {changeTable} from './functions';
import Categories from './Categories';


class Left extends Component {

    constructor(props) {
        super();
        this.state = {
          popQuizes: [],
          recQuizes: [],
          statistics: [],
          nOfTable: 0,
          isAdmin: props.isAdmin
        }
    }

    componentDidMount() {
        fetch('http://localhost:8080/ServletPopularQuizzes')
            .then((response) => {
                response.json().then((data) => {
                    this.setState({popQuizes: data});
                })
            });
        fetch('http://localhost:8080/ServletRecentQuizzes')
            .then((response) => {
                response.json().then((data) => {
                    this.setState({recQuizes: data});
                })
            });
        fetch('http://localhost:8080/ServletStatistics')
            .then((response) => {
                response.json().then((data) => {
                    this.setState({statistics: data});
                })
            });
    }

    render() {
        return (
            <div className = "left">
                <div className = "profile">
                    <a href = {"profile/:userId"} style = {{textDecoration:"none"}}>
                        <table className="profTable">
                            <tbody>
                                <tr>
                                    <td>
                                        <img className="picture" src="male.jpg"></img>
                                        <p className = "nickName"><font size="4">vigaca</font></p>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </a>
                </div>
                <div className="quizes">
                    <table className="quizesTable">
                        <thead>
                            <tr>
                                <th colSpan="3">
                                    <i onClick = {()=>changeTable(this, this.state.nOfTable)} className="leftArrow"></i>
                                    {this.state.nOfTable === 0 ? "Popular quizes" : " Recent quizes "}
                                    <i onClick = {()=>changeTable(this, this.state.nOfTable)}  className="rightArrow"></i>
                                </th>
                            </tr>
                        </thead>
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>QuizName</th>
                                <th>Creator</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                            this.state.nOfTable === 0 ? 
                                (this.state.popQuizes.map((quiz, i) => {
                                    return <tr key={i}>
                                        <td><a href={"/quiz/" + quiz.id}>{i + 1}</a></td>
                                        <td><a href={"/quiz/" + (i + 1)}>{quiz.title}</a></td>
                                        <td><a href={"/profile/" + quiz.friendId}>{quiz.creator}</a></td>
                                    </tr>
                                }))
                            :
                                (this.state.recQuizes.map((quiz, i) => {
                                    return <tr key={i}>
                                        <td><a href={"/quiz/" + quiz.id}>{i + 1}</a></td>
                                        <td><a href={"/quiz/" + (i + 1)}>{quiz.title}</a></td>
                                        <td><a href={"/profile/" + quiz.friendId}>{quiz.creator}</a></td>
                                    </tr>
                                }))
                            }
                        </tbody>
                    </table>
                </div>
                <Categories></Categories>
                {this.state.isAdmin ? 
                <div className="statistics" style={{textAlign:"center", marginBottom:"3vh"}}>
                    Today registered {this.state.statistics.registeredInOneDay} users<br></br>
                    This month registered {this.state.statistics.registeredInOneMonth} users<br></br>
                    This year registered {this.state.statistics.registeredInOneYear} users<br></br>
                    Total users {this.state.statistics.totalUsers}<br></br>
                    Today written {this.state.statistics.writtenQuizzesInOneDay} quizzes<br></br>
                    This month written {this.state.statistics.writtenQuizzesInOneMonth} quizzes<br></br>
                    This year written {this.state.statistics.writtenQuizzesInOneYear} quizzes<br></br>
                    Total written quizzes {this.state.statistics.totalWrittenQuizzes}<br></br>
                </div> : null
                }
            </div>
        );
    }
}



export default Left;