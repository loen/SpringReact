import React from 'react';
import {Link} from 'react-router';


class Layout extends React.Component {

    render(){
        return(<div>
            <div>
                <ul className="nav nav-pills nav-stacked">
                    <li>
                       <Link to='/blog'>Blog</Link>
                    </li>
                    <li>
                        <Link to='/picture'>Picture</Link>
                    </li>
                    <li>
                        <Link to='/video'>Video</Link>
                    </li>
                </ul>
            </div>
            {this.props.children}
        </div>);
    }
}

export default Layout