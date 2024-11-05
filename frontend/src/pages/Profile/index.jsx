import { Description } from "../../components/description/Description";
import { Box } from "../../components/box/Box";
import { Profile } from "../../components/profile/Profile";

export function ProfilePage() {
  return (
    <>
      <Description />
      <Box name="Meu Perfil" />
      <Profile />
    </>
  )
}