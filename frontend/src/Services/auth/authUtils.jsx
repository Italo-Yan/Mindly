import * as zod from "zod";

export const loginSchema = zod.object({
  email: zod.string().min(1, { message: "O campo Login é obrigatório." }),
  password: zod
    .string()
    .min(4, { message: "A senha deve ter pelo menos 4 caracteres." }),
});

export const validateLoginData = (data) => {
  return loginSchema.safeParse(data);
};