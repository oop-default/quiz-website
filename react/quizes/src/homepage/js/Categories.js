import React,{Component} from 'react';
import '../css/Categories.css'

const categories = [
    {id: 1, category: "imnairi"},
];

class Categories extends Component{
    constructor() {
        super();
        this.state = {
          categories: categories
        }
    }

    render() {
        return (
            <div>
                <table className="categoriesTable">
                    <thead>
                        <th>Categories</th>
                    </thead>
                    <tbody>
                    {
                        this.state.categories.map((category) => {
                            return <tr key={category.id}>
                                <td>
                                    {category.category}
                                </td>
                            </tr>
                        })
                    }
                    </tbody>
                </table>
            </div>
        )
    }

}

export default Categories;