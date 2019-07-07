import React,{Component} from 'react';
import '../css/Activities.css';
import Achievements from './Achievements';
import QuizTaking from './QuizTaking';
import CreatedQuizes from './CreatedQuizes';

class Activities extends Component {
    constructor() {
        super();
    }

    render() {
        return (
            <div className="activities">
                <QuizTaking></QuizTaking>
                <CreatedQuizes></CreatedQuizes>
                <Achievements></Achievements>
            </div>
        );
    }





}

export default Activities;