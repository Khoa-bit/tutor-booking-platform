import type { NextPage } from "next";
import Head from "next/head";
import Image from "next/image";
import { useRouter } from "next/router";
import { SubmitHandler, useForm } from "react-hook-form";
import styles from "../styles/Home.module.css";

type Inputs = {
  username: string;
  password: string;
};

const Login: NextPage = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<Inputs>();

  const onSubmit: SubmitHandler<Inputs> = (data) => console.log(data);

  return (
    <div className="absolute flex h-full w-full items-center justify-center">
      <Head>
        <title>Login</title>
        <meta name="description" content="Login to HomeTutor" />
        <link rel="icon" href="/static/favicon.ico" />
      </Head>

      <form className="flex flex-col" onSubmit={handleSubmit(onSubmit)}>
        <input type="text" {...register("username", { required: true })} />
        {errors.username && <span>This field is required</span>}

        <input type="password" {...register("password", { required: true })} />
        {errors.password && <span>This field is required</span>}

        <input type="submit" />
      </form>
    </div>
  );
};

export default Login;
