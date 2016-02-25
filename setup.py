from setuptools import setup, find_packages

setup(
    name='PyDisplay',
    version='0.1dev',
    package=['PyDisplay'],
    package_dir=find_packages('PyDisplay'),
    requires=['numpy'],
    # include_package_data=True,
)
