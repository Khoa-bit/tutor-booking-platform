import HomeTutorIcon from "@components/Icons/HomeTutorIcon";
import type { NextPage } from "next";
import { useRouter } from "next/router";
import Image from "next/image";
import classroom0 from "public/static/classroom0.jpg";
import Link from "next/link";
import { LOGIN_PATH } from "@lib/clientConstants";

interface AuthLayoutProps {
  children: React.ReactElement;
}

const AuthLayout = ({ children }: AuthLayoutProps) => {
  const router = useRouter();

  return (
    <>
      <Image
        src={classroom0}
        alt="Picture of the classroom"
        layout="fill"
        objectFit="cover"
      ></Image>

      <div className="absolute flex h-full w-full items-center justify-center bg-gray-800/60">
        <section className="flex max-w-lg flex-col items-center justify-center gap-3 rounded-lg bg-white p-7">
          <Link href="/">
            <a>
              <HomeTutorIcon className="w-32"></HomeTutorIcon>
            </a>
          </Link>
          <h1 className="text-3xl font-bold">Welcome to Home Tutor</h1>
          <p className="font-semibold text-gray-600">
            The best tutor finding website
          </p>
          {children}
          <p className="w-10/12">
            Blanditiis repellendus autem quibusdam possimus tenetur? Non omnis
            mollitia voluptate?
          </p>

          {router.pathname.startsWith(LOGIN_PATH) && (
            <p className="font-semibold">
              Dont&apos;t have an account?{" "}
              <Link href="/signup">
                <a className="font-bold hover:underline">Sign up</a>
              </Link>
            </p>
          )}
        </section>
      </div>
    </>
  );
};

export default AuthLayout;
