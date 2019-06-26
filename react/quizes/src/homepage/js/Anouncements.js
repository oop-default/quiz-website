import React, { Component } from 'react';

import '../css/Anouncements.css'
const anouncements = [
    {statement: "nothing for now"},
    {statement: "same here"},
    {statement: "nothing for now"},
    {statement: "same here"},
    {statement: "nothing for now"},
    {statement: "same here"},
    {statement: "nothing for now"},
    {statement: "same here"},
    {statement: "nothing for now"},
    {statement: "same here"},
    {statement: "nothing for now"},
    {statement: "same here"},
    {statement: "nothing for now"},
    {statement: "same here"},
    {statement: "nothing for now"},
    {statement: "same here"},
    {statement: "nothing for now"},
    {statement: "same here"},
    {statement: "nothing for now"},
    {statement: "same here"},
    {statement: "nothing for now"},
    {statement: "same here"},
    {statement: "nothing for now"},
    {statement: "same here"}
  ];


  class Anouncements extends Component{
    constructor() {
        super();
        this.state = {
          anouncements: anouncements,
        }
    }


    render() {
        return (
            <div className = "anouncements">
                <table className="anoncTable">
                    <tbody>
                        {
                        this.state.anouncements.map((anons) => {
                            return <tr>
                            <td><a style={{textDecoration:"none"}} href="https://www.facebook.com">{anons.statement}</a></td>
                            </tr>
                        })
                        }
                    </tbody>
                </table>
            </div>
        );
    }



  }

  export default Anouncements;

