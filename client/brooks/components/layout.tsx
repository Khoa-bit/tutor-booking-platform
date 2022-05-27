import { AuthUser } from "@lib/model/AuthUser";
import { ReactElement } from "react";
import Footer from "./Footer";
import NavBar from "./NavBar";

interface LayoutProps {
  children: ReactElement;
  user: AuthUser | null;
}

const Layout = ({ children, user }: LayoutProps) => {
  return (
    <>
      <NavBar user={user}></NavBar>
      <main>{children}</main>
      <Footer></Footer>
    </>
  );
};

export default Layout;
