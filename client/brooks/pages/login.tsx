import { SERVER_DOMAIN } from "@lib/clientConstants";
import axios from "axios";
import type { NextPage } from "next";
import Head from "next/head";
import { useRouter } from "next/router";
import { SubmitHandler, useForm } from "react-hook-form";

type Inputs = {
  username: string;
  password: string;
};

const Login: NextPage = () => {
  const router = useRouter();
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<Inputs>();

  const onSubmit: SubmitHandler<Inputs> = async (data) => {
    const fetchPromise = await axios.post(
      `${SERVER_DOMAIN}/auth/signin`,
      {
        username: data.username,
        password: data.password,
      },
      {
        withCredentials: true,
      }
    );

    if (fetchPromise.status == 200) {
      // Successfully logged in
      router.push("/");
    } else {
      // Incorrect credentials
    }
  };

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
