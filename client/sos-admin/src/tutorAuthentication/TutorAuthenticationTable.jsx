import React from "react";
import TutorAuthenticationRow from "./TutorAuthenticationRow";
import axios from "axios";

class TutorAuthenticationTable extends React.Component {
  state = {
    tutorsAuthentication: []
  };

  componentDidMount() {
    axios
      .get("http://localhost:8080/api/tutors-authentication")
      .then((res) => {
        const tutorsAuthentication = res.data;
        this.setState({ tutorsAuthentication });
      })
      .catch((error) => console.log(error));
  }

  render() {
    return (
      <table className="table table-striped">
        <thead className="sticky-top">
          <tr className="bg-success">
            <th className="text-light">Tutor ID</th>
            <th className="text-light">Username</th>
            <th className="text-light">Password</th>
            <th className="text-light"></th>
            <th className="text-light"></th>
          </tr>
        </thead>
        <tbody>
          {/*{this.state.tutorsAuthentication.map((tutor) => (
            <TutorRow tutor={tutor} />
          ))} */}

          {Object.keys(this.state.tutorsAuthentication).map((key) => (
            <TutorAuthenticationRow key={key} tutorAuthentication={this.state.tutorsAuthentication[key]} />
          ))}
        </tbody>
      </table>
    );
  }
}

export default TutorAuthenticationTable;
