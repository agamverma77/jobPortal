import { Menu, rem, Avatar, Switch, useMantineColorScheme } from '@mantine/core';
import {
  IconLogout2,
  IconUserCircle,
  IconSun,
  IconMoonStars,
  IconMoon,
} from '@tabler/icons-react';
import { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import { removeUser } from '../../Slices/UserSlice';
import { removeJwt } from '../../Slices/JwtSlice';

const ProfileMenu = () => {
  const user = useSelector((state: any) => state.user);
  const profile = useSelector((state: any) => state.profile);
  const [opened, setOpened] = useState(false);
  const dispatch = useDispatch();

  // ✅ Dark mode toggle
  const { colorScheme, toggleColorScheme } = useMantineColorScheme();
  const dark = colorScheme === 'dark';

  const handleLogout = () => {
    dispatch(removeUser());
    dispatch(removeJwt());
  };

  return (
    <Menu shadow="md" width={200} opened={opened} onChange={setOpened}>
      <Menu.Target>
        <div className="flex items-center gap-2 cursor-pointer">
          <div className="xs-mx:hidden">{user.name}</div>
          <Avatar
            src={profile.picture ? `data:image/jpeg;base64,${profile.picture}` : '/avatar.png'}
            alt="it's me"
          />
        </div>
      </Menu.Target>

      <Menu.Dropdown onChange={() => setOpened(true)}>
        <Link to="/profile">
          <Menu.Item
            leftSection={<IconUserCircle style={{ width: rem(14), height: rem(14) }} />}
          >
            Profile
          </Menu.Item>
        </Link>

        {/* ✅ Dark mode toggle */}
        <Menu.Item
          leftSection={<IconMoon style={{ width: rem(14), height: rem(14) }} />}
          rightSection={
            <Switch
              size="sm"
              color="dark"
              className="cursor-pointer"
              onLabel={
                <IconSun style={{ width: rem(14), height: rem(14) }} stroke={2.5} color="yellow" />
              }
              offLabel={
                <IconMoonStars style={{ width: rem(14), height: rem(14) }} stroke={2.5} color="cyan" />
              }
              checked={dark}
              onChange={toggleColorScheme}
            />
          }
        >
          Dark Mode
        </Menu.Item>

        <Menu.Divider />

        <Menu.Item
          onClick={handleLogout}
          color="red"
          leftSection={<IconLogout2 style={{ width: rem(14), height: rem(14) }} />}
        >
          Logout
        </Menu.Item>
      </Menu.Dropdown>
    </Menu>
  );
};

export default ProfileMenu;
