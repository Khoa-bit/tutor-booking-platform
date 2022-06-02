import React from "react";
import { Link } from "react-router-dom";
import ClassFilter from "./ClassFilter";
import ClassTable from "./ClassTable";

class Classes extends React.Component {
  render() {
    return (
      <div className="container">
        <header className="mt-5">
          <h1 className="text-center">Classes</h1>
        </header>
        <main className="mt-5">
          <ClassFilter />
          <div className="table-responsive border border-dark mt-3">
            <ClassTable />
          </div>
        </main>
        <footer className=" mt-5 mb-5">
          <div className="d-flex justify-content-center align-items-center">
            <Link to="/" className="btn btn-warning mx-3">
              Create new Class
            </Link>
            <Link to="/" className="btn btn-success mx-3">
              Home
            </Link>
          </div>
        </footer>
      </div>
    );
  }
}

export default Classes;
