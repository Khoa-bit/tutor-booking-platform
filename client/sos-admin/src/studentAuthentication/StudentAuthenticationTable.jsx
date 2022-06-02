import React from "react";
import StudentAuthenticationRow from "./StudentAuthenticationRow";
import axios from "axios";

class StudentAuthenticationTable extends React.Component {
  state = {
    studentsAuthentication: []
  };

  componentDidMount() {
    axios
      .get("http://localhost:8080/api/students-authentication")
      .then((res) => {
        const studentsAuthentication = res.data;
        this.setState({ studentsAuthentication });
      })
      .catch((error) => console.log(error));
  }

  render() {
    return (
      <table className="table table-striped">
        <thead className="sticky-top">
          <tr className="bg-success">
            <th className="text-light">Student ID</th>
            <th className="text-light">Username</th>
            <th className="text-light">Password</th>
            <th className="text-light"></th>
            <th className="text-light"></th>
          </tr>
        </thead>
        <tbody>
          {/*{this.state.StudentsAuthentication.map((Student) => (
            <StudentRow Student={Student} />
          ))} */}

          {Object.keys(this.state.studentsAuthentication).map((key) => (
            <StudentAuthenticationRow key={key} studentAuthentication={this.state.studentsAuthentication[key]} />
          ))}
        </tbody>
      </table>
    );
  }
}

export default StudentAuthenticationTable;
